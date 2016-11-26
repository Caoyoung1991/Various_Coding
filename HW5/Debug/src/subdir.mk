################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Table.cpp \
../src/grades.cpp \
../src/listFuncs.cpp \
../src/pa5list.cpp 

OBJS += \
./src/Table.o \
./src/grades.o \
./src/listFuncs.o \
./src/pa5list.o 

CPP_DEPS += \
./src/Table.d \
./src/grades.d \
./src/listFuncs.d \
./src/pa5list.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cross G++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


