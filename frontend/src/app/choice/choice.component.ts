import { Component, OnInit } from '@angular/core';
import { ChoiceService } from 'src/app/service/choice.service';
import {ActivatedRoute} from '@angular/router';
import { Choice } from '../choice-template'
import { User } from '../user-template';

@Component({
  selector: 'app-choice',
  templateUrl: './choice.component.html',
  styleUrls: ['./choice.component.css']
})
export class ChoiceComponent implements OnInit {

  id: number;
  nom: string;
  prenom: string;
  email: string;
  debut: string;
  fin: string;
  user = new User();
  choice = new Choice();

  constructor(private route: ActivatedRoute, private ChoiceService: ChoiceService) {
    this.route.params.subscribe( params => this.id = params['id'] );
    console.log(this.id);
  }

  ngOnInit(): void {

  }

  saveChoice() {
    console.log(this.fin)
    this.user.nom = this.nom;
    this.user.prenom = this.prenom;
    this.user.email = this.email;
    this.choice.debut = this.debut;
    this.choice.fin = this.fin;
    this.ChoiceService.saveChoiceService(this.id, this.choice);
    this.ChoiceService.saveUserService(this.id, this.user);
  }

}
