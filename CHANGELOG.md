# CHANGELOG

## Version 2.5.2

- Fixing crash caused by ignore properties = false on Jackson

## Version 2.5.1

- Fixing some crash

## Version 2.5.0

- Changing floors behaviour to only have in the floor selector the floors for which there is a layer visible on the screen. Changed the behaviour of getFloors and on didChangeFloors accordingly.

## Version 2.4.0

- Floors can now have decimal (Double) values, handy for mezzanines or intermediate levels
- Added class MWZCoordinatesInVenue that needs to be used to compute directions from coordinate to coordinate

## Version 2.3.6

- Fixed followUserMode change on floorChange from sdk
- Added method startDirection(Direction direction, boolean preventFitbounds)

## Version 2.3.5

- Api key must be passed in MWZAccountManager. Api key in xml does not work anymore
- Added event onVenueEnter and onVenueExit

## Version 2.3.4

- Fixed mainColor affects userPosition

## Version 2.3.3

- Floor, zoom and center map options now accessible using getters on the map after init
- Using Integer in MWZMapOptions instead of int
- Fixed bug with click on external place
- Limited required attributes for external place to : venueId, translations, floor and geometry
- Removed highlight on place click

## Version 2.3.2

- Fixed bug with options

## Version 2.3.1

- Fixed bug with bounds map option
- Fixed bug with zoom not set properly when passed as map option

## Version 2.3.0

- Added mainColor options
- Added support for external places
- Added option displayFloorControl
- Addes markerDisplay options

## Version 2.2.0

- Deprecated methods using latitude, longitude and floor to be replaced by methods using MWZCoordinate or MWZUserPosition
- Added a set of methods to promote places (make them displayed with highest priority)
- Added a set of methods to ignore places (hide them)
- Added onDestroy method on MWZMapView that has to be called on activity destroy when iBeacon location is used

## Version 2.1.4

- Added documentation for setUniverseForVenue methods
- Added map option to hide user position button
- Fixed bug with translation subTitle

## Version 2.1.3

- Fixed bug with the search menu in the demo app
- Added API call to retrieve universes for an organization, MWZMapView.setUniverseForVenue can now take a MWZUniverse 
- Deprecated onMapLoad method, even if still fired for now; Use onMapLoaded instead 
- Added getBounds method for geometries

## Version 2.1.2

- Added get methods to MWZSearchable
- Added search bar example in demo app

## Version 2.1.1

- Added API methods to get venues, places and placeLists
- Added geometry and other parameters to MWZVenue

## Version 2.1.0

- showdirection is deprecated. Use MWZApi.getDirections and MWZMapView.startDirections methods instead.
- MWZLatLon and MWZLatLonBounds are replaced by MWZCoordinate and MWZBounds.
- MWZPosition has been removed. MWZPlace, MWZPlaceList and MWZCoordinate now implement the MWZDirectionPoint interface.
- Introduced MWZApi, a wrapper to easily retrieve Mapwize data. All API methods that were previously linked to the map have been moved to MWZApi.
- Introduced MWZAccountManager to share API Key between MWZApi and MWZMapView
- The MWZPlace object now includes the geometry
- MWZPlaceStyle has been renamed in MWZStyle

## Version 1.7.2

- Fixed bug related to measurement parsing and directions
- Added proguard rules

## Version 1.7.1

- Improved location service. Requires bluetooth et bluetooth_admin permissions to use iBeacons.
- Introduced startLocation and stopLocation methods in MWZMapView.
- onMapLoad only then the map is fully loaded.
- Added minZoom and maxBounds map options.

## Version 1.6.0

- Added completionHandlers to all methods which can fail
- Added support for multilingual venues
- General improvements and bugfix

## Version 1.5.0

- Added locationEnabled and beaconsEnables in MapOptions
- Added placeList support with api methods and directions to a list

## Version 1.4.1

- Added method to stop showing directions.
- Added method to set user position with accuracy.
- Added methods to set top and bottom margins.
- Added the possibility to display user heading. See code in example app.
- Added methods to query venues and places on id, name and alias.
- Added caching to limit network traffic and resist to loss of connection. Call refresh on the map to force the update of the data.
