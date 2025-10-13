/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { RuralPropertiesServiceService } from './ruralPropertiesService.service';

describe('Service: RuralPropertiesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RuralPropertiesServiceService]
    });
  });

  it('should ...', inject([RuralPropertiesServiceService], (service: RuralPropertiesServiceService) => {
    expect(service).toBeTruthy();
  }));
});
