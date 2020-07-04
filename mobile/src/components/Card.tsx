import * as React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import { Feather as Icon } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';

interface Props {
    weight: String,
    expected: String
    uf: String,
    city: String,
    street: String,
    number: String,
    category: String,
    color: String,
    latitude: String,
    longitude: String,
    freight: String,
    next: String,
}

export default function Card({
    weight,
    expected,
    uf,
    city,
    street,
    number,
    category,
    color="green",
    latitude,
    longitude,
    freight,
    next,
}: Props) {

    const navigation = useNavigation();

    function onPress() {
        navigation.navigate('Detail', {
            weight,
            expected,
            uf,
            city,
            street,
            number,
            category,
            color,
            latitude,
            longitude,
            freight,
            next,
        });
    }

    return (
        <>
            <TouchableOpacity onPress={onPress}>
                <View style={[styles.container, {
                    marginTop: 25,
                    }]}
                >
                    <View style={[ styles.box, styles.box3, { backgroundColor: `${color}` } ]} />
                    <View style={styles.box}>
                        <Text style={{ marginLeft: 10 }}>{city}, {uf}</Text>
                        <Text style={{ marginLeft: 10 }}>{weight}</Text>
                    </View>
                    <View style={[styles.box, styles.box2, { justifyContent: 'space-between', padding: 7 }]} >
                        <Text>{category}</Text>
                        <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                            <Text>{expected}  </Text>
                            <Icon name="map-pin" size={16} color='red' />
                        </View>
                        <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                            <Text>R$ {freight}</Text>
                        </View>
                    </View>
                </View>
            </TouchableOpacity>
        </>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 10,
        flexDirection: 'row',
        justifyContent: 'space-between',
        width: "100%",
        marginBottom: 50,
    },

    box: {
        flex: 6,
        height: 70,
        justifyContent: 'center',
        alignItems: 'flex-start',
        backgroundColor: 'white',
    },

    box2: {
        flex: 3,
        alignItems: 'flex-end',
        marginRight: 10
    },

    box3: {
        flex: .1,
    },   
});