import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IngresarvehiculoComponent } from './ingresarvehiculo.component';

describe('IngresarvehiculoComponent', () => {
  let component: IngresarvehiculoComponent;
  let fixture: ComponentFixture<IngresarvehiculoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IngresarvehiculoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IngresarvehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
