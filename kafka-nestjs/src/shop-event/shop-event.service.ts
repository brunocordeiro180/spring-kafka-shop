import { Injectable } from '@nestjs/common';

global.shopEvents = [];

@Injectable()
export class ShopEventService {
  findAll() {
    return global.shopEvents;
  }

  insertShopEvent(event) {
    const eventJson = JSON.parse(event);
    global.shopEvents.push(eventJson);
  }
}
