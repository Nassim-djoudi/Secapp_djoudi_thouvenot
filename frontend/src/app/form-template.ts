import { User } from "./user-template";
import { Choice } from "./choice-template";

export class Form {
    id: number;
    titre: string;
    lieu: string;
    organisateur: User;
    password: string;
    choixPossible: Choice[];
    participants: User[];
    choixSelectionne: User;
}