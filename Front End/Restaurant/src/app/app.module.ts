import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { DataTablesModule } from 'angular-datatables';
import { HttpClientModule} from '@angular/common/http';
import { MenuListComponent } from './menu-list/menu-list.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { RestaurantService } from './restaurant.service';
import { SearchComponent } from './search/search.component';
import { SearchByDishNameComponent } from './search-by-dish-name/search-by-dish-name.component';

@NgModule({
  declarations: [
    AppComponent,
    RestaurantComponent,
    MenuListComponent,
    CategoryListComponent,
    SearchComponent,
    SearchByDishNameComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DataTablesModule,
    HttpClientModule
  
  ],
  providers: [RestaurantService],
  bootstrap: [AppComponent]
})
export class AppModule { }
