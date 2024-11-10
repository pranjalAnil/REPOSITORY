package com.example.Task1_event_Scheduling.services.implementation;
import com.example.Task1_event_Scheduling.entities.CustomEvent;
import com.example.Task1_event_Scheduling.payloads.CustomEventDto;
import com.example.Task1_event_Scheduling.repositories.CustomEventsRepo;
import com.example.Task1_event_Scheduling.services.CustomEventService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CustomEventServiceImpl implements CustomEventService {
    Timestamp timestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
    @Autowired
    CustomEventsRepo customEventsRepo;
    public CustomEventDto addEvent(CustomEventDto customEventDto){
        customEventDto.setTimestamp(timestamp());
        customEventDto.setStatus(0);
        CustomEvent customEvent =new CustomEvent();
        BeanUtils.copyProperties(customEventDto,customEvent);

        customEvent= customEventsRepo.save(customEvent);
        BeanUtils.copyProperties(customEvent,customEventDto);
        return customEventDto;
    }


    @Scheduled(cron = "0 0 2 1,15 * ?")
    public void archiveAndDeleteOldEvents()  {

        List<CustomEvent> oldEvents = customEventsRepo.findAll();

        if (!oldEvents.isEmpty()) {
            writeEventsToCSV(oldEvents);
           for(CustomEvent customEvent:oldEvents){
               if (customEvent.getStatus()==1){
                   customEventsRepo.delete(customEvent);
               }
           }

        }


    }
    private void writeEventsToCSV(List<CustomEvent> oldEvents) {
        System.out.println("Writing csv file");
        String filePath = Paths.get("archived_events.csv").toString();
        try (FileWriter fileWriter = new FileWriter(filePath, true); // Append mode
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                     .withHeader("ID", "Timestamp", "CustomerId", "Events", "Status"))) {

            for (CustomEvent event : oldEvents) {
                csvPrinter.printRecord(
                        event.getId(),
                        event.getTimestamp(),
                        event.getCustomerId(),
                        event.getEvent(),
                        event.getStatus()
                );
                event.setStatus(1);

            }
            System.out.println("Completed writing");
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


