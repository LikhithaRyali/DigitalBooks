import { TestBed } from '@angular/core/testing';

import { ReaderServicesService } from './reader-services.service';

describe('ReaderServicesService', () => {
  let service: ReaderServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReaderServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
