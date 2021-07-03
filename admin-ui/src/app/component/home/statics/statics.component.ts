import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-statics',
  templateUrl: './statics.component.html',
  styleUrls: ['./statics.component.css']
})
export class StaticsComponent implements OnInit {

  single = [
    {
      "name": "Toplam Kullanıcı",
      "value": 134
    },
    {
      "name": "Toplam Bildirim Sayısı",
      "value": 4230
    },
    {
      "name": "Toplam Gönderilen Mail",
      "value": 839
    },
    {
      "name": "Toplam Konu Sayısı",
      "value": 17
    },
    {
      "name": "Toplam Yorum Sayısı",
      "value": 253
    },
    {
      "name": "Toplam Görüntülenme",
      "value": 172321
    },
    {
      "name": "Toplam Beğenilen Yorum",
      "value": 283
    },
    {
      "name": "Toplam Favori Konu",
      "value": 172
    },
    {
      "name": "Toplam Kategori ",
      "value": 11
    },
    {
      "name": "Toplam Rol ",
      "value": 3
    },
    {
      "name": "Toplam İletişim Mesajı ",
      "value": 36
    },
    {
      "name": "Toplam Hata Mesajı ",
      "value": 21
    }
  ]
  multi = [
    {
      "name": "Equatorial Guinea",
      "series": [
        {
          "value": 4068,
          "name": "2016-09-13T18:11:25.840Z"
        },
        {
          "value": 3485,
          "name": "2016-09-18T13:56:45.131Z"
        },
        {
          "value": 6966,
          "name": "2016-09-24T00:01:39.376Z"
        },
        {
          "value": 6760,
          "name": "2016-09-15T09:30:32.670Z"
        },
        {
          "value": 6003,
          "name": "2016-09-23T11:10:15.524Z"
        }
      ]
    },
    {
      "name": "Denmark",
      "series": [
        {
          "value": 6108,
          "name": "2016-09-13T18:11:25.840Z"
        },
        {
          "value": 2876,
          "name": "2016-09-18T13:56:45.131Z"
        },
        {
          "value": 2160,
          "name": "2016-09-24T00:01:39.376Z"
        },
        {
          "value": 6935,
          "name": "2016-09-15T09:30:32.670Z"
        },
        {
          "value": 5287,
          "name": "2016-09-23T11:10:15.524Z"
        }
      ]
    },
    {
      "name": "Tokelau",
      "series": [
        {
          "value": 6290,
          "name": "2016-09-13T18:11:25.840Z"
        },
        {
          "value": 3797,
          "name": "2016-09-18T13:56:45.131Z"
        },
        {
          "value": 4084,
          "name": "2016-09-24T00:01:39.376Z"
        },
        {
          "value": 5168,
          "name": "2016-09-15T09:30:32.670Z"
        },
        {
          "value": 3986,
          "name": "2016-09-23T11:10:15.524Z"
        }
      ]
    },
    {
      "name": "Malawi",
      "series": [
        {
          "value": 6085,
          "name": "2016-09-13T18:11:25.840Z"
        },
        {
          "value": 4099,
          "name": "2016-09-18T13:56:45.131Z"
        },
        {
          "value": 5735,
          "name": "2016-09-24T00:01:39.376Z"
        },
        {
          "value": 6488,
          "name": "2016-09-15T09:30:32.670Z"
        },
        {
          "value": 4795,
          "name": "2016-09-23T11:10:15.524Z"
        }
      ]
    },
    {
      "name": "Myanmar",
      "series": [
        {
          "value": 6749,
          "name": "2016-09-13T18:11:25.840Z"
        },
        {
          "value": 2741,
          "name": "2016-09-18T13:56:45.131Z"
        },
        {
          "value": 3536,
          "name": "2016-09-24T00:01:39.376Z"
        },
        {
          "value": 6344,
          "name": "2016-09-15T09:30:32.670Z"
        },
        {
          "value": 2074,
          "name": "2016-09-23T11:10:15.524Z"
        }
      ]
    }
  ]
  
  view: any[] = [900, 400];
  view2: any[] = [1000, 500];
  mobile: any [] = [
    {
      "name": "Telefon",
      "value": 2785
    },
    {
      "name": "Bilgisayar",
      "value": 13589
    }
  ];
  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };
  cardColor: string = '#232837';
    // options
    legend: boolean = true;
    showLabels: boolean = true;
    animations: boolean = true;
    xAxis: boolean = true;
    yAxis: boolean = true;
    showYAxisLabel: boolean = true;
    showXAxisLabel: boolean = true;
    xAxisLabel: string = 'Tarih';
    yAxisLabel: string = 'Analitik';
    timeline: boolean = true;
  constructor() { }

  onSelect(event) {
    console.log(event);
  }
  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  ngOnInit(): void {
  }

}
