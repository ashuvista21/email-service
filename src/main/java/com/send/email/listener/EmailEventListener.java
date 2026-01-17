package com.send.email.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.send.email.dtos.EmailEvent;
import com.send.email.service.EmailService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailEventListener {

	private final EmailService emailService;

	// Simple listener (auto-deserialize to EmailEvent). If factory configured for
	// MANUAL ack,
	// this listener will still work but you won't commit offsets automatically.
	/*
	 * @KafkaListener(topics = "${kafka.topic.email-events}", groupId =
	 * "${kafka.consumer.group-id}", containerFactory =
	 * "kafkaListenerContainerFactory") public void listen(ConsumerRecord<String,
	 * EmailEvent> record) { EmailEvent event = record.value();
	 * System.out.println("Received event (auto-handled): " + event.eventId() +
	 * " to: " + event.recipient()); // process event: send email, persist, etc. //
	 * if manual commit required, use the variant below with Acknowledgment }
	 */

	// Manual ack example: get Acknowledgment and commit when processing is
	// successful
	@KafkaListener(topics = "${kafka.topic.email-events}", groupId = "${kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
	public void listenWithAck(@Payload EmailEvent event, Acknowledgment ack) {
		try {
			System.out.println("Received (manual ack) email to: " + event.recipient());
			// process event
			emailService.sendEmailViaListenerAsync(event).thenRun(() -> {
				ack.acknowledge();
				System.out.println("Send");
			});
			// on success:
			// commits offset
		} catch (Exception ex) {
			// handle failure (log, metrics) - do not ack if you want retries
			throw ex; // ErrorHandler configured in container will handle retry/backoff
		}
	}
}
