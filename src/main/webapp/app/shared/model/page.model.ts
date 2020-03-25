export interface IPage {
  id?: number;
  seoName?: string;
  seoTitle?: string;
  seoDescription?: string;
  seoKeys?: string;
  contentTop?: string;
  contentBottom?: string;
}

export class Page implements IPage {
  constructor(
    public id?: number,
    public seoName?: string,
    public seoTitle?: string,
    public seoDescription?: string,
    public seoKeys?: string,
    public contentTop?: string,
    public contentBottom?: string
  ) {}
}
