package com.example.Task1_event_Scheduling.services;

import com.example.Task1_event_Scheduling.entities.CustomEvent;
import com.example.Task1_event_Scheduling.repositories.CustomEventsRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerEventService {
    Timestamp timestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
    @Autowired
    CustomEventsRepo customEventsRepo;
    public CustomEvent addEvent(CustomEvent customEvent){
        customEvent.setTimestamp(timestamp());
        return customEventsRepo.save(customEvent);
    }

    @Scheduled(cron = "0 02 17 * * ?")
    public void archiveAndDeleteOldEvents() {
//        .minusDays(0)
        System.out.println("time calculation");
        LocalDateTime cutoffDate = LocalDateTime.now();
        Timestamp cutoffTimestamp = Timestamp.valueOf(cutoffDate);

        List<CustomEvent> oldEvents = customEventsRepo.findByTimestampBefore(cutoffTimestamp);

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


