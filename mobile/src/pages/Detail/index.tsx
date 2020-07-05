import React from 'react';
import { Feather as Icon, FontAwesome } from '@expo/vector-icons';
import { useNavigation, useRoute } from '@react-navigation/native'
import { RectButton } from 'react-native-gesture-handler';

import MapView, { Marker, Polyline } from 'react-native-maps';

import { View, StyleSheet, TouchableOpacity, Text, Image, Linking, Platform } from 'react-native';
import Constants from 'expo-constants';

interface Params {
  initialPosition: String,
  weight: String,
  volume: String,
  expected: String,
  uf: String,
  city: String,
  street: String,
  number: String,
  category: String,
  color: String,
  latitudeCurrent: String,
  longitudeCurrent: String,
  latitudeDestination: String,
  longitudeDestination: String,
  next: String,
}

const Detail = () => {
  const navigation = useNavigation();
  const route = useRoute();

  const routeParams = route.params as Params;
  
  const initialPositions = routeParams.initialPosition;
  const initialPosition = initialPositions.split(",");

  const weight = routeParams.weight;
  const volume = routeParams.volume;
  const expected = routeParams.expected;
  const uf = routeParams.uf;
  const city = routeParams.city;
  const street = routeParams.street;
  const number = routeParams.number;
  const category = routeParams.category;
  const color = routeParams.color;

  const latitudeCurrent = Number(routeParams.latitudeCurrent);
  const longitudeCurrent = Number(routeParams.longitudeCurrent);

  const latitudeDestination = Number(routeParams.latitudeDestination);
  const longitudeDestination = Number(routeParams.longitudeDestination);

  function handleNavigateBack() {
    navigation.goBack();
  }

  // function openGps (lat: Number, lng: Number) {
  function openGps () {

    // if (Platform.OS === 'ios') {

    //   var scheme = 'maps:';
    //   var url = scheme + `${lat},${lng}`;

    //   Linking.openURL(url);

    //   return;
    // }

    Linking.openURL(`https://www.google.com.br/maps/dir/${MyLoaction.latitude},${MyLoaction.longitude}/${LoadCurrent.latitude},${LoadCurrent.longitude}/${LoadDestination.latitude},${LoadDestination.longitude}`);

  }

  const MyLoaction = {
    latitude: Number(initialPosition[0]),
    longitude: Number(initialPosition[1])
  }
  const LoadCurrent = {
    latitude: Number(latitudeCurrent),
    longitude: Number(longitudeCurrent)
  };

  const LoadDestination = {
    latitude: Number(latitudeDestination),
    longitude: Number(longitudeDestination)
  };

  return (
      <>
          <View style={styles.container}>
            <TouchableOpacity onPress={handleNavigateBack} style={{ marginLeft: -10 }}>
                <Icon name="arrow-left" size={20} color='black' />
            </TouchableOpacity>

            <Text style={styles.pointName}>Carga {category}</Text>
            <Text style={[styles.pointItems, {paddingLeft: 20}]}>Carga: {weight}</Text>
            <Text style={[styles.pointItems, {paddingLeft: 20}]}>Volume: {volume}</Text>
            <Text style={[styles.pointItems, {paddingLeft: 20}]}>Entregar até: {expected}</Text>

            <View style={styles.address}>
                <Text style={[styles.addressTitle, {paddingLeft: 20}]}>Endereço</Text>
                <Text style={[styles.addressContent, {paddingLeft: 20}]}>{city}, {uf} - {street}, {number}</Text>
            </View>
          </View>
          <View style={styles.mapContainer}>
            <MapView
              style={{ flex: 1 }}
              initialRegion={{
                latitude: MyLoaction.latitude,
                longitude: MyLoaction.longitude,
                latitudeDelta: 0.1,
                longitudeDelta: 0.1
              }}
            >
              <Marker
                style={styles.mapMarker}
                onPress={() => handleNavigateBack()}
                coordinate={{
                    latitude: MyLoaction.latitude,
                    longitude: MyLoaction.longitude,
                }}
              >
                <View style={styles.mapMarkerContainer}>
                      <Text style={styles.mapMarkerTitle}>Você</Text>
                  </View>
              </Marker>
              <Marker
                style={styles.mapMarker}
                onPress={() => handleNavigateBack()}
                coordinate={{
                    latitude: LoadCurrent.latitude,
                    longitude: LoadCurrent.longitude,
                }}
              >
                <View style={styles.mapMarkerContainer}>
                      <Text style={styles.mapMarkerTitle}>Encomenda</Text>
                  </View>
              </Marker>
              <Marker
                style={styles.mapMarker}
                onPress={() => handleNavigateBack()}
                coordinate={{
                    latitude: LoadDestination.latitude,
                    longitude: LoadDestination.longitude,
                }}
              >
                <View style={styles.mapMarkerContainer}>
                      <Text style={styles.mapMarkerTitle}>Destino</Text>
                  </View>
              </Marker>
              <Polyline coordinates={[MyLoaction,LoadCurrent]} />
              <Polyline coordinates={[LoadCurrent, LoadDestination]} />

            </MapView>
            
          </View>
          <View style={styles.footer}>
              <RectButton style={styles.button} onPress={() => openGps()}>
                  <FontAwesome name="whatsapp" size={20} color='#FFF' />
                  <Text style={styles.buttonText}>Map</Text>
              </RectButton>
          </View>
      </>
  );
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
      paddingHorizontal: 22,
      paddingTop: 20 + Constants.statusBarHeight,
    },
  
    pointImage: {
      width: '100%',
      height: 120,
      resizeMode: 'cover',
      borderRadius: 10,
      marginTop: 32,
    },
  
    pointName: {
      color: '#322153',
      fontSize: 28,
      fontFamily: 'Ubuntu_700Bold',
      marginTop: 24,
    },
  
    pointItems: {
      fontFamily: 'Roboto_400Regular',
      fontSize: 16,
      lineHeight: 24,
      marginTop: 8,
      color: '#6C6C80'
    },
  
    address: {
      marginTop: 32,
    },
    
    addressTitle: {
      color: '#322153',
      fontFamily: 'Roboto_500Medium',
      fontSize: 16,
    },
  
    addressContent: {
      fontFamily: 'Roboto_400Regular',
      lineHeight: 24,
      marginTop: 8,
      color: '#6C6C80'
    },
  
    footer: {
      borderTopWidth: StyleSheet.hairlineWidth,
      borderColor: '#999',
      paddingVertical: 20,
      paddingHorizontal: 32,
      flexDirection: 'row',
      justifyContent: 'center'
    },
    
    button: {
      width: '48%',
      backgroundColor: '#34CB79',
      borderRadius: 10,
      height: 50,
      flexDirection: 'row',
      justifyContent: 'center',
      alignItems: 'center'
    },
  
    buttonText: {
      marginLeft: 8,
      color: '#FFF',
      fontSize: 16,
      fontFamily: 'Roboto_500Medium',
    },
    
  
    mapContainer: {
      flex: 1,
      width: '100%',
      borderRadius: 10,
      overflow: 'hidden',
      marginTop: 16,
    },
  
    map: {
      width: '100%',
      height: '100%',
    },
  
    mapMarker: {
      width: 90,
      height: 80, 
    },
  
    mapMarkerContainer: {
      width: 90,
      height: 40,
      backgroundColor: 'white',
      borderRadius: 8,
      overflow: 'hidden',
      alignItems: 'center',
      justifyContent: 'center'
    },
  
  
    mapMarkerTitle: {
      flex: 1,
      fontFamily: 'Roboto_400Regular',
      color: 'black',
      fontSize: 13,
    },
  });

export default Detail;