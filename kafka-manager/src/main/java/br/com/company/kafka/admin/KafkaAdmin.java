package br.com.company.kafka.admin;

import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class KafkaAdmin {
    public static void create(String topicName, int partitions, short replications, AdminClient adminClient) {
        final var newTopic = new NewTopic(topicName, partitions, replications);
        var topics = Collections.singletonList(newTopic);
        try {
            final var result = adminClient.createTopics(topics);
            result.all().get();
        }catch (final Exception e){
            throw new RuntimeException("Failed to create topic: " + topicName, e);
        }
    }

    public static void list(AdminClient adminClient) throws ExecutionException, InterruptedException {
        var listTopicsResult = adminClient.listTopics();
        listTopicsResult.names().get().forEach(System.out::println);
    }

    public static void describe(String topicName, AdminClient adminClient) throws ExecutionException, InterruptedException {
        var topicNames = Collections.singletonList(topicName);
        DescribeTopicsResult topics = adminClient.describeTopics(topicNames);
        topics.all().get().forEach((x,y) -> System.out.println(x + " " + y.topicId() + " " + y.partitions()));
    }

    public static void delete(String topicName, AdminClient adminClient) {
        var topicNames = Collections.singletonList(topicName);
        try {
            DeleteTopicsResult topics = adminClient.deleteTopics(topicNames);
            topics.all().get();
        }catch (final Exception e){
            throw new RuntimeException("Failed to delete topic " + topicName, e);
        }
    }

    public static void listCG(AdminClient adminClient) throws ExecutionException, InterruptedException {
        var cgs = adminClient.listConsumerGroups();
        cgs.all().get().forEach(cg -> System.out.println(cg.groupId()));
    }

    public static void deleteCG(String groupId, AdminClient adminClient) {
        var groups = Collections.singletonList(groupId);
        try {
            DeleteConsumerGroupsResult cgs = adminClient.deleteConsumerGroups(groups);
            cgs.all().get();
        }catch (final Exception e){
            throw new RuntimeException("Failed to delete cg: "+ groupId, e);
        }
    }

    public static void describeCluster(AdminClient adminClient) throws ExecutionException, InterruptedException {
        DescribeClusterResult cluster = adminClient.describeCluster();
        System.out.println(cluster.clusterId().get());
    }
}
