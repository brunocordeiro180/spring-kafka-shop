import { Injectable, OnModuleInit } from '@nestjs/common';
import { ConsumerService } from './kafka/consumer.service';
import { ShopEventService } from './shop-event/shop-event.service';

@Injectable()
export class ShopConsumer implements OnModuleInit {
  constructor(
    private readonly consumerService: ConsumerService,
    private readonly shopEventService: ShopEventService,
  ) {}

  async onModuleInit() {
    await this.consumerService.consume(
      { topic: 'SHOP_TOPIC_EVENT' },
      {
        eachMessage: async ({ topic, partition, message }) => {
          console.log({
            value: message.value.toString(),
            topic: topic.toString(),
            partition: partition.toString(),
          });
          this.shopEventService.insertShopEvent(message.value.toString());
        },
      },
    );
  }
}
