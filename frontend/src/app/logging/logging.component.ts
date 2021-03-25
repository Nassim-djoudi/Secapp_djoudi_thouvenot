import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { FormService } from 'src/app/service/form.service';
import { Form } from '../form-template';
import { User } from '../user-template';

@Component({
  selector: 'app-logging',
  templateUrl: './logging.component.html',
  styleUrls: ['./logging.component.css']
})

/**
 * Composant permettant d'obtenir les informations d'un sondage à partir d'un ID 
 * et du mot passe
 */

export class LoggingComponent implements OnInit {

  id: string;
  password_log: string;
  form = new Form();
  titre: string;
  lieu: string;
  nom: string;
  prenom: string;
  email: string;
  secure = true;
  secure_status = "Activée";

  constructor(private FormService: FormService, private router: Router) { }

  ngOnInit(): void {
  }

  async goToForm() {

    // Recuperation des donnees dans l'observable contenant le sondage provenant d'une methode GET
    await this.FormService.getForm(this.id, this.password_log, String(this.secure)).then(data => {this.form = data;});
    console.log(this.form);

    // Creation d'un objet sur la methode POST nous retourne null en cas de mauvais inputs
    if (this.form == null) {
      this.form = new Form();
      this.form.organisateur = new User();
    }

    // Affichage de resultat
    this.titre = this.form.titre;
    this.lieu = this.form.lieu;
    this.nom = this.form.organisateur.nom;
    this.prenom = this.form.organisateur.prenom;
    this.email = this.form.organisateur.email;
    
  }

  update() {
    console.log(this.secure);
    if (!this.secure) {
      this.secure_status = "Désactivée";
    } else {
      this.secure_status = "Activée";
    }
  }

}
