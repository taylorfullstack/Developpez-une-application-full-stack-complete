import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {SidebarService} from "./services/sidebar.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {
  title: string = "MDD"
  @ViewChild('sidenav') sidenav!: MatSidenav;

  constructor(private sidebarService: SidebarService) {}

  ngAfterViewInit() {
    this.sidebarService.setSidenav(this.sidenav);
  }

  public closeSidebar() {
    this.sidebarService.closeSidebar();
  }

  get isOpen() {
    return this.sidebarService.isSidebarOpen();
  }
}
