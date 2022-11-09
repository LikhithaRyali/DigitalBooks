import { TestBed } from '@angular/core/testing';

import { AuthorBooksServiceService } from './author-books-service.service';

describe('AuthorBooksServiceService', () => {
  let service: AuthorBooksServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorBooksServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
