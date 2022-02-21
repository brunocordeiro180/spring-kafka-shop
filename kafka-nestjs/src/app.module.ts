import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { KafkaModule } from './kafka/kafka.module';
import { ShopConsumer } from './shop.consumer';
import { ShopEventModule } from './shop-event/shop-event.module';
import { ShopEventService } from './shop-event/shop-event.service';

@Module({
  imports: [KafkaModule, ShopEventModule],
  controllers: [AppController],
  providers: [AppService, ShopConsumer, ShopEventService],
})
export class AppModule {}
