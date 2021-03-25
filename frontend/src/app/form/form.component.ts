import { Component, OnInit } from '@angular/core';
import { FormService } from 'src/app/service/form.service';
import { User } from "src/app/user-template";
import { Form } from "src/app/form-template";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})

/**
 * Composant permettant d'ajouter un nouveau sondage
 */

export class FormComponent implements OnInit {

  titre: string;
  lieu: string;
  email: string;
  nom: string;
  prenom: string;
  password: string;
  organisateur = new User();
  form = new Form();

  constructor(private FormService: FormService, private matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  // Methode de sauvegarde d'un nouveau sondage
  saveForm() {
    
    // Objet User de l'organisateur
    this.organisateur.email = this.email;
    this.organisateur.nom = this.nom;
    this.organisateur.prenom = this.prenom;

    // Objet Form du sondage
    this.form.titre = this.titre;
    this.form.lieu = this.lieu;
    this.form.organisateur = this.organisateur;
    this.form.password = this.password;

    // Appel de la methode POST dans le service form et gestion des erreurs
    this.FormService.saveFormService(this.form).
    subscribe(data => {
      this.getUrl(), console.log(data);},
      (err:any) => {
        this.openPopUp(err['error']['id'], 2000), console.log(err);
      });
    }

  // Methode de recuperation de l'URL de connexion associee au sondage
  async getUrl() {
    await this.FormService.getUrlService(this.titre, this.email).subscribe(data => {
      this.openPopUp(data['id'], 30000);});    
  }

  // Methode d'affichage d'un message pop up
  openPopUp(message: string, duration: number) {
    
    this.matSnackBar.open(message, "Fermer", {
      duration: duration,
    });
  }
}
