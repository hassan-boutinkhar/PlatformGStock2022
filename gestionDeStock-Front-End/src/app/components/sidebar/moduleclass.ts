export interface Menu {
  id?: number;
  titre?: String;
  icon?: string;
  url?: string;
  sousmenu?: Array<Menu>;
  class?: string;
}
