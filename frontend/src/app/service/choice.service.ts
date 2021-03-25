import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http"
import { Choice } from '../choice-template';
import { User } from '../user-template';

@Injectable({
  providedIn: 'root'
})

/**
 * Service qui gere la communication avec le backend pour les choix et des users associe aux choix
 */

export class ChoiceService {

  constructor(private http: HttpClient) { }

  // Methode POST de sauvegarde d'un sondage
  saveChoiceService(id: number, choice: Choice) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post<Choice>('http://localhost:8080/choice/' + id, choice, httpOptions).subscribe();
    
  }
  
  // Methode POST de sauvegarde d'un user lors de l'ajout d'un choix
  saveUserService(id: number, user: User) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post<User>('http://localhost:8080/choice/add-user/' + id, user, httpOptions).subscribe();
  }

}
