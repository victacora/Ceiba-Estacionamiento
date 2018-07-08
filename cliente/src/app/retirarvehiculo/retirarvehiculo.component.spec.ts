import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetirarvehiculoComponent } from './retirarvehiculo.component';

describe('RetirarvehiculoComponent', () => {
  let component: RetirarvehiculoComponent;
  let fixture: ComponentFixture<RetirarvehiculoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetirarvehiculoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetirarvehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
