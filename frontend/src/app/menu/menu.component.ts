import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

/**
 * Composant permettant de gerer le menu d'accueil de l'application
 */

export class MenuComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  // Aller vers le composant d'ajout d'un sondage
  urlAddForm() {
    this.router.navigateByUrl('/add-form')
  }

  // Aller vers le composant pour consulter un sondage
  consultForm() {
    this.router.navigateByUrl('/logging');
  }

}
