import { Component, OnInit,OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Params } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { map } from "rxjs/operators";
import {RestaurantDetails} from '../restaurantDetails';
import {Response} from '@angular/http';
import { RestaurantService } from '../restaurant.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-search-by-dish-name',
  templateUrl: './search-by-dish-name.component.html',
  styleUrls: ['./search-by-dish-name.component.css']
})
export class SearchByDishNameComponent implements OnInit {

 title=" Search Dishes by Price, Restaurant ";
  public dishes:any;
  dtTrigger:Subject<any> = new Subject();
  dtOptions: DataTables.Settings = {};

  constructor( private route: ActivatedRoute,
    private location: Location,
    private http: HttpClient, 
    private restaurantService: RestaurantService) { }

  ngOnInit() {
  console.log('Dishes are fetched');

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    this.restaurantService.getDishes().subscribe(
          dishes => { this.dishes = dishes; 
          this.dtTrigger.next();
         
      });
  }

 ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }


private extractData(res: Response) {
    const body = res.json();
    return body.data || {};
  }
  
  
 
}
