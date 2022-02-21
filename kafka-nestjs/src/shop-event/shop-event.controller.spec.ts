import { Test, TestingModule } from '@nestjs/testing';
import { ShopEventController } from './shop-event.controller';
import { ShopEventService } from './shop-event.service';

describe('ShopEventController', () => {
  let controller: ShopEventController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [ShopEventController],
      providers: [ShopEventService],
    }).compile();

    controller = module.get<ShopEventController>(ShopEventController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
