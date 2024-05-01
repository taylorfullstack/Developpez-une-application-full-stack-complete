import {Component} from '@angular/core';
import {SidebarService} from '../../services/sidebar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  constructor(private sidebarService: SidebarService) {}

  public toggleSidebar():void {
    this.sidebarService.toggleSidebar();
  }
}
