import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthLayoutComponent } from './auth.layout.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AuthLayoutComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AuthLayoutComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'front'`, () => {
    const fixture = TestBed.createComponent(AuthLayoutComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('MDD');
  });
});
