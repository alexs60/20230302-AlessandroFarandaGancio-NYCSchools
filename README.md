# nycschools coding challenge

Sample app that use the SODA API provided from NYC available here: https://opendata.cityofnewyork.us/

## Requirements

- Display a list of NYC High Schools (s3k6-pzi2)
- Selecting a school should show additional information about the school
  - display all the SAT scores (f9bf-2cp4)
  - display additional other information (up to the candidate)

### Future developments
- add loading of more item on down scrolling, using pagination provided from SODA services (in ListFragment)
- add an overlay when loading items (ListFragment and DetailsFragment)
- display school "overview_paragragraph" (DetailsFragment)
- manage and centralize error handling from services
- add imagelogo in list items
- add search button and search from SODA services function (ListFragment)
- use RxAndroid for loading datas from multiple services (DetailsFragment)
- add gradle app flavours for multiple environment 
