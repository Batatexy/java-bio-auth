import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../models/user/user';

@Component({
  selector: 'app-userCard',
  templateUrl: './userCard.component.html',
  styleUrls: ['./userCard.component.css']
})
export class UserCardComponent implements OnInit {
  @Input() user!: User;

  ngOnInit() {
  }

}
