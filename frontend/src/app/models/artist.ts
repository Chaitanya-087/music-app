import { ARole } from "./arole";

export interface Artist {
    id: number,
    username: string,
    country?: string,
    roles: ARole[]
}
