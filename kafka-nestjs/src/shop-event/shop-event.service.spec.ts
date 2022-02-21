import { Test, TestingModule } from '@nestjs/testing';
import { ShopEventService } from './shop-event.service';

describe('ShopEventService', () => {
  let service: ShopEventService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [ShopEventService],
    }).compile();

    service = module.get<ShopEventService>(ShopEventService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
