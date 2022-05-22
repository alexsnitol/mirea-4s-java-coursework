const ctx1 = document.getElementById('myChart1').getContext('2d');
const myChart1 = new Chart(ctx1, {
    type: 'line',
    data: {
        labels: ['', 'окт. 2018', 'фев. 2018', 'июл. 2019', 'ноя. 2019', 'апр. 2020', 'июл. 2020', 'янв. 2021', 'май 2021', 'окт. 2021', ''],
        datasets: [{
            label: ' руб./кв.м.',
            data: [, 170000, 174000, 175000, 178000, 182000, 184500, 210000, 220000, 239000, ],
            backgroundColor: '#129DFF',
            borderColor: '#129DFF',
            borderWidth: 2,
            radius: 3,
            tension: 0.4
        }]
    },
    options: {
        maintainAspectRatio: false,
        interaction: {
            intersect: false,
            mode: 'index',
        },
        hoverRadius: 6,
        scales: {
            y: {
                ticks: {
                    color: '#F4F4F4',
                    font: {
                        family: 'Raleway',
                        size: 14,
                        style: 'normal',
                    },
                    padding: 10,
                },
                grid: {
                    display: true,
                    color: "rgba(255,255,255,0.1)"
                }
            },
            x: {
                ticks: {
                    color: '#F4F4F4',
                    font: {
                        family: 'Raleway',
                        size: 14,
                        style: 'normal',
                    },
                    padding: 10
                },
                grid: {
                    display: false,
                    color: "rgba(255,255,255,0.1)"
                }
            }
        },
        title: {
            display: false,
        },
        plugins: {
            legend: {
                display: false,
            }
        }
    }
});

const ctx2 = document.getElementById('myChart2').getContext('2d');
const myChart2 = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: ['', 'окт. 2018', 'фев. 2019', 'июл. 2019', 'сен. 2019', 'ноя. 2019', 'янв. 2020', 'май 2020', 'июл. 2020', 'авг. 2020 - н.в.', ''],
        datasets: [{
            label: ' Процентная ставка',
            data: [, 5.56, 6.26, 5.44, 5.09, 4.58, 4.41, 4.02, 3.46, 3.35, ],
            backgroundColor: '#129DFF',
            borderColor: '#129DFF',
            borderWidth: 2,
            radius: 3,
            tension: 0.4
        }]
    },
    options: {
        maintainAspectRatio: false,
        interaction: {
            intersect: false,
            mode: 'index',
        },
        hoverRadius: 6,
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    color: '#F4F4F4',
                    font: {
                        family: 'Raleway',
                        size: 14,
                        style: 'normal',
                    },
                    padding: 10,
                    callback: function(value, index, values) {
                        return  value + ' %';
                    }
                },
                grid: {
                    display: true,
                    color: "rgba(255,255,255,0.1)"
                }
            },
            x: {
                ticks: {
                    color: '#F4F4F4',
                    font: {
                        family: 'Raleway',
                        size: 14,
                        style: 'normal',
                    },
                    padding: 10
                },
                grid: {
                    display: false,
                    color: "rgba(255,255,255,0.1)"
                }
            }
        },
        title: {
            display: false,
        },
        plugins: {
            legend: {
                display: false,
            }
        }
    }
});