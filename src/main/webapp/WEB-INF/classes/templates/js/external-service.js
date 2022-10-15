const externalService = {};
(function (module) {
    const _regionsList = {
        eu: 'Europe',
        na: 'North America',
        sa: 'South America',
        af: 'Africa',
        as: 'Asia',
        oc: 'Oceania',
    };

    const _countriesList = [
        {
            name: 'Ukraine',
            flagURL: 'https://www.countryflags.io/ua/flat/64.png',
            region: _regionsList.eu,
            area: 603500,
            capital: 'Kiev',
            languages: {ukr: 'Ukrainian'},
        },
        {
            name: 'United Kingdom',
            flagURL: 'https://www.countryflags.io/gb/flat/64.png',
            region: _regionsList.eu,
            area: 242900,
            capital: 'London',
            languages: {eng: 'English'},
        },
        {
            name: 'United States',
            flagURL: 'https://www.countryflags.io/um/flat/64.png',
            region: _regionsList.na,
            area: 9372610,
            capital: 'Washington D.C.',
            languages: {eng: 'English'},
        },
    ];

    module.getCountryListByRegion = (region) =>
        _countriesList.filter((country) => country.region === region);
    module.getCountryListByLanguage = (language) =>
        _countriesList.filter((country) =>
            Object.values(country.languages).includes(language)
        );
    module.getRegionsList = () => Object.values(_regionsList);
    module.getLanguagesList = () => [
        ..._countriesList.reduce((languagesList, country) => {
            const countryLanguages = Object.values(country.languages);

            return languagesList.add(...countryLanguages);
        }, new Set()),
    ];
})(externalService);
