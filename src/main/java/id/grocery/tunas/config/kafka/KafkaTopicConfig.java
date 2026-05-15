package id.grocery.tunas.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic requestExportTopic(@Value("${messaging.outgoing.export-report-request.topic}") String exportReportRequestTopic){
        return new NewTopic(exportReportRequestTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic resultExportTopic(@Value("${messaging.outgoing.export-report-result.topic}") String exportReportResultTopic){
        return new NewTopic(exportReportResultTopic, 1, (short) 1);
    }
}
