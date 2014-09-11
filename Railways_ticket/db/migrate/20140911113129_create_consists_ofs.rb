class CreateConsistsOfs < ActiveRecord::Migration
  def change
    create_table :consists_ofs do |t|
      t.integer :station_ID
      t.integer :train_ID
      t.integer :stop_number

      t.timestamps
    end
  end
end
