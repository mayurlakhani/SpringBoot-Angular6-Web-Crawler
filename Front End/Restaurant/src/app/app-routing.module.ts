import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { MenuListComponent } from './menu-list/menu-list.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { SearchByDishNameComponent } from './search-by-dish-name/search-by-dish-name.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: RestaurantComponent },
  { path: 'searchdish', component: SearchByDishNameComponent },
  { path: 'categorylist/:id', component: CategoryListComponent },
  { path: 'dishlist/:id', component: MenuListComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
