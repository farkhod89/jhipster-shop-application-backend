import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterShopApplicationTestModule } from '../../../test.module';
import { FileDetailComponent } from 'app/entities/file/file-detail.component';
import { File } from 'app/shared/model/file.model';

describe('Component Tests', () => {
  describe('File Management Detail Component', () => {
    let comp: FileDetailComponent;
    let fixture: ComponentFixture<FileDetailComponent>;
    const route = ({ data: of({ file: new File(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterShopApplicationTestModule],
        declarations: [FileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load file on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.file).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});