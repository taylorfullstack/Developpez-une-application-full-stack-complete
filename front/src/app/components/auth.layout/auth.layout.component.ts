import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {SidebarService} from "../../services/sidebar.service";

@Component({
  selector: 'app-root',
  templateUrl: './auth.layout.component.html',
  styleUrls: ['./auth.layout.component.scss']
})
export class AuthLayoutComponent implements AfterViewInit {
  title: string = "MDD"
  @ViewChild('sidenav') sidenav!: MatSidenav;

  constructor(private sidebarService: SidebarService) {}

  ngAfterViewInit():void {
    this.sidebarService.setSidenav(this.sidenav);
  }

  public closeSidebar():void {
    this.sidebarService.closeSidebar();
  }

  get isOpen() {
    return this.sidebarService.isSidebarOpen();
  }
}
