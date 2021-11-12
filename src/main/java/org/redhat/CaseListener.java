package org.redhat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jbpm.casemgmt.api.event.*;

import java.util.Date;
import java.util.EventListener;
import java.util.Properties;
import java.util.concurrent.Future;

public class CaseListener implements CaseEventListener {



    @Override
    public void afterCaseStarted(CaseStartEvent event) {

        CaseDefinition caseDefinition = new CaseDefinition(event.getCaseId(),"Case Started",null,null, new Date());
        pushToKafka(caseDefinition);
    }

    private void pushToKafka(CaseDefinition caseDefinition) {
        try {
            Future<RecordMetadata> out = producer.send(new ProducerRecord<String,
                    String>("case_events", caseDefinition.getCaseId(), new ObjectMapper().writeValueAsString(caseDefinition)));



        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        System.out.println("case listener clean up");
            producer.flush();

            producer.close();

    }

    @Override
    public void afterCaseDataAdded(CaseDataEvent event) {

        CaseDefinition caseDefinition = new CaseDefinition(event.getCaseId(),"Case Data Added",event.getData(),null, new Date());
        pushToKafka(caseDefinition);
    };

    @Override
    public void afterCaseDataRemoved(CaseDataEvent event) {
        CaseDefinition caseDefinition = new CaseDefinition(event.getCaseId(),"Case Data Removed",event.getData(),null, new Date());
        pushToKafka(caseDefinition);
    };

    @Override
    public void afterCaseClosed(CaseCloseEvent event) {
        CaseDefinition caseDefinition = new CaseDefinition(event.getCaseId(),"Case Closed",null,null, new Date());
        pushToKafka(caseDefinition);
    };

    @Override
    public void afterCaseCommentAdded(CaseCommentEvent event) {
        CaseComment caseComment = new CaseComment(event.getComment().getComment(),event.getComment().getAuthor());
        CaseDefinition caseDefinition = new CaseDefinition(event.getCaseId(),"Comments Added",null,caseComment,new Date());
        pushToKafka(caseDefinition);
    };

    @Override
    public void afterCaseReopen(CaseReopenEvent event) {
        CaseDefinition caseDefinition = new CaseDefinition(event.getCaseId(),"Case Reopened",null,null, new Date());
        pushToKafka(caseDefinition);
    }

    com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    Properties properties = null;
    KafkaProducer<String,String> producer = null;

    public CaseListener() {
        System.out.println("startup case listener");
        String boostrapServers = "localhost:9092";
        properties= new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,boostrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<String, String>(properties);

    }
}
