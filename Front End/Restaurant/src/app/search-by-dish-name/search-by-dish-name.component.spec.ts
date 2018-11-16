import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByDishNameComponent } from './search-by-dish-name.component';

describe('SearchByDishNameComponent', () => {
  let component: SearchByDishNameComponent;
  let fixture: ComponentFixture<SearchByDishNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchByDishNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchByDishNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
