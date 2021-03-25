import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http"
import { Observable } from "rxjs";
import { Form } from '../form-template';


@Injectable({
  providedIn: 'root'
})

/**
 * Service qui gere la communication avec le backend pour les sondages
 */

export class FormService {

  constructor(private http: HttpClient) { }

  // Methode POST de sauvegarde d'un formulaire
  saveFormService(form: Form): Observable<any>{
    return this.http.post<Form>('http://localhost:8080/add-form', form);
  }

  // Methode GET pour obtenir le lien de connexion associee à un sondage pour ajouter 
  // des choix
  getUrlService(titre_send: string, email_send: string): Observable<any> {
    return this.http.get('http://localhost:8080/add-form/get-url', {params: {
      titre: titre_send,
      email: email_send
    }});
  }

  // Methode GET pour obtenir un sondage à partir d'un ID d'un sondage et du mot
  // passe associe
  getForm(id_send: string, password_send: string, secure_send: string): Promise<Form> {
    return this.http.get<Form>('http://localhost:8080/logging', {params: {
      id: id_send,
      password: password_send,
      secure: secure_send
    }}).toPromise();    
  }
}
