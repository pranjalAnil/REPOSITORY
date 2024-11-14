package com.example.task_5_cashbackTxn_peanding_failed_entries.entities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.Map;


@Entity
@Data
public class CashbackTxn5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long mobileNumber;
    private  String offerId;
    private String upiRequestId;
    private int cbAmount;
    private  String status;
    // 0-success, 1 pending, 2-failed
    private String CBELIGIBLE;
    @Column(columnDefinition = "JSON")
    private String cbTxnInfo;

    private Timestamp updatedAt;
    private Timestamp createdAt;
    // ObjectMapper for JSON serialization
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to set cbTxnInfo as a JSON string
    public void setCbTxnInfo(Map<String, Object> cbTxnInfoMap) {
        try {
            this.cbTxnInfo = objectMapper.writeValueAsString(cbTxnInfoMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // Method to get cbTxnInfo as a Map
    public Map<String, Object> getCbTxnInfo() {
        try {
            return objectMapper.readValue(cbTxnInfo, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
