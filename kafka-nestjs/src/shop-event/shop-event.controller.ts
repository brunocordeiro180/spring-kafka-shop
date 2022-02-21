import { Controller, Get } from '@nestjs/common';
import { ShopEventService } from './shop-event.service';

@Controller('shop-event')
export class ShopEventController {
  constructor(private readonly shopEventService: ShopEventService) {}

  @Get()
  findAll() {
    return this.shopEventService.findAll();
  }
}
