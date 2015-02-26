__author__ = 'Syngularity'
import os
import time


def run():
    times_ = []
    for i, image in enumerate(os.listdir('img/')):
        if '.png' in image:
            c_time = time.time()
            os.system('java -jar build/A.jar ' + 'img/' + image + ' ' + image.replace('.png', '.out'))
            times_.append(time.time() - c_time)
            print 'It takes', times_[i], 'to process', os.getcwd() + '\\' + image
    total_time = sum(times_)
    print 'There was', len(times_), 'image, and it takes', total_time, 'to process them.'


def check():
    is_it_good = []
    for out_f_test in os.listdir('out/'):
        if '.out' in out_f_test:
            try:
                test_num = int(open('out/' + out_f_test, 'r').read())
            except TypeError:
                pass

            try:
                get_num = int(open(out_f_test, 'r').read())
            except TypeError:
                pass

            if test_num == get_num:
                is_it_good.append(True)
            else:
                is_it_good.append(False)
    return is_it_good


def check_print(is_it_good):
    i = 0
    for test_item in is_it_good:
        if test_item:
            print 'The program find correctly the number of Illuminati'
        else:
            print 'The program is give bed value'
        i += 1
    if i == len(is_it_good):
        print 'The program is work absolutely correctly, and find all Illuminati'
    elif i == 0:
        print 'The program is work absolutely bad'
    else:
        print 'The program is work barely good'


if __name__ == '__main__':
    run()
    is_it_good = check()
    check_print(is_it_good)