class CreateTrains < ActiveRecord::Migration
  def change
    create_table :trains do |t|
      t.string :train_name
      t.string :train_type
      t.string :seats_class1
      t.string :seats_class2
      t.string :fare_class1
      t.string :fare_class2
      t.string :available_days

      t.timestamps
    end
  end
end
