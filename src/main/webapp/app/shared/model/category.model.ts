import { Moment } from 'moment';

export interface ICategory {
  id?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  name?: string;
  nameUz?: string;
  alias?: string;
  sorting?: number;
  status?: boolean;
  main?: boolean;
  fileId?: number;
  pageId?: number;
  pageUzId?: number;
  children?: ICategory[];
  parentId?: number;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public name?: string,
    public nameUz?: string,
    public alias?: string,
    public sorting?: number,
    public status?: boolean,
    public main?: boolean,
    public fileId?: number,
    public pageId?: number,
    public pageUzId?: number,
    public children?: ICategory[],
    public parentId?: number
  ) {
    this.status = this.status || false;
    this.main = this.main || false;
  }
}
