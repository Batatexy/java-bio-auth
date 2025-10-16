import { Component, Input } from '@angular/core';
import { RuralProperties } from '../../models/ruralProperties/ruralProperties';

@Component({
  selector: 'app-rural-properties-card',
  templateUrl: './ruralPropertiesCard.component.html',
  styleUrls: ['./ruralPropertiesCard.component.css']
})
export class RuralPropertiesCardComponent {
  @Input() ruralProperties!: RuralProperties;

  ngOnInit() {

  }
}
