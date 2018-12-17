import { TestBed } from '@angular/core/testing';

import { TiposRiscoService } from './tipos-risco.service';

describe('TiposRiscoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TiposRiscoService = TestBed.get(TiposRiscoService);
    expect(service).toBeTruthy();
  });
});
