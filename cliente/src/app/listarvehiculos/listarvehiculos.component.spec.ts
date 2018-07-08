import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarvehiculosComponent } from './listarvehiculos.component';

describe('ListarvehiculosComponent', () => {
  let component: ListarvehiculosComponent;
  let fixture: ComponentFixture<ListarvehiculosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarvehiculosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarvehiculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
