import { Component, OnInit,OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Params } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { map } from "rxjs/operators";
import {RestaurantDetails} from '../restaurantDetails';
import {Response} from '@angular/http';
import { RestaurantService } from '../restaurant.service';
@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit,OnDestroy {
  title= "Welcome to RestaurantDetails";
  dtOptions: DataTables.Settings = {};
  restaurantDetails:any;
  dtTrigger:Subject<any> = new Subject();
  public restaurants;
  
  constructor(private http: HttpClient, 
              private restaurantService: RestaurantService){ }
              
  ngOnInit() {
    	this.getRest();
      console.log('restaurants are fetched');
      this.dtOptions = {
        pagingType: 'full_numbers',
        pageLength: 3
      };
       this.restaurantService.getRestaurants().subscribe(
          data => { this.restaurantDetails = data; 
          this.dtTrigger.next();
         
      });

    console.log('Dishes are fetched');

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }


  private extractData(res: Response) {
      const body = res.json();
      return body.data || {};
    }

  getRest(){
	
  return this.restaurantService.getRestaurants().subscribe(
  
      data => {this.restaurants = data;},
      err => {console.log(err);}
  );

}
}
