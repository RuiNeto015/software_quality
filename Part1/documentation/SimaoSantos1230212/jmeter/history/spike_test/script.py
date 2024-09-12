import pandas as pd
from datetime import datetime, timedelta


def execute():
    excel_file_path = './result_data.csv'
    df = pd.read_csv(excel_file_path)
    data_list = df.to_dict(orient='records')
    sum1, sum2, sum3, sum4 = 0, 0, 0, 0
    count1, count2, count3, count4 = 0, 0, 0, 0

    for obj in data_list:
        if (obj['timeStamp'] > timestamp_converter(1699797638434, 360) and obj['timeStamp'] < timestamp_converter(1699797638434, 400)):
            sum1 += obj['elapsed']
            count1 += 1
        if (obj['timeStamp'] > timestamp_converter(1699797638434, 480) and obj['timeStamp'] < timestamp_converter(1699797638434, 520)):
            sum2 += obj['elapsed']
            count2 += 1
        if (obj['timeStamp'] > timestamp_converter(1699797638434, 600) and obj['timeStamp'] < timestamp_converter(1699797638434, 640)):
            sum3 += obj['elapsed']
            count3 += 1
        if (obj['timeStamp'] > timestamp_converter(1699797638434, 720) and obj['timeStamp'] < timestamp_converter(1699797638434, 760)):
            sum4 += obj['elapsed']
            count4 += 1

    print("Average response times: ")
    print("Spike 1: ", sum1 / count1)
    print("Spike 2: ", sum2 / count2)
    print("Spike 3: ", sum3 / count3)
    print("Spike 4: ", sum4 / count4)


def timestamp_converter(initial, target):
    timestamp = initial / 1000.0
    dt = datetime.fromtimestamp(timestamp)

    new_dt = dt + timedelta(seconds=target)
    new_timestamp = int(new_dt.timestamp() * 1000)

    return new_timestamp


execute()
