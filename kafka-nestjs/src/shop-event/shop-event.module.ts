import { Module } from '@nestjs/common';
import { ShopEventService } from './shop-event.service';
import { ShopEventController } from './shop-event.controller';

@Module({
  controllers: [ShopEventController],
  providers: [ShopEventService],
})
export class ShopEventModule {}
