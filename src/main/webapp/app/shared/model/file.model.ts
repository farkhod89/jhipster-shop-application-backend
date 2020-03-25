import { Moment } from 'moment';

export interface IFile {
  id?: number;
  createdAt?: Moment;
  fileName?: string;
  fileType?: string;
  filePath?: string;
}

export class File implements IFile {
  constructor(
    public id?: number,
    public createdAt?: Moment,
    public fileName?: string,
    public fileType?: string,
    public filePath?: string
  ) {}
}
